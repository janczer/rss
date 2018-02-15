/*
 * MIT License
 *
 * Copyright (c) 2018 janczer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.janczer;

import com.janczer.model.Channel;
import com.janczer.model.Post;
import com.janczer.repository.ChannelRepository;
import com.janczer.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class HomeController {

  private final ChannelRepository channelRepository;

  private final PostRepository postRepository;

  @Inject
  public HomeController(ChannelRepository channelRepository, PostRepository postRepository) {
    this.channelRepository = channelRepository;
    this.postRepository = postRepository;
  }

  @RequestMapping("/")
  public String home(Model model) {
    final Iterable<Channel> channels = channelRepository.findAll();
    model.addAttribute("channels", channels);
    return "home";
  }

  @RequestMapping("/generate")
  public String addData() {
    Channel c = new Channel();
    c.setLink("link");
    c.setTitle("test channel");
    c.setDescription("channel for test");

    channelRepository.save(c);

    for (int i = 0; i < 10; i++) {
      Post p = new Post();
      p.setChannel(c);
      p.setLink(String.format("post %d link", i));
      p.setDescription(String.format("post %d description", i));
      p.setTitle(String.format("post %d title", i));
      postRepository.save(p);
    }

    return "ok";
  }
}
