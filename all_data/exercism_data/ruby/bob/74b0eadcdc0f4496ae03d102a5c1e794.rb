#!/usr/bin/env ruby

class Bob
  def hey(msg)
    Response.new(msg).speak
  end

  class Response
    def initialize(msg)
      @heard = WordsHeard.new(msg)
    end

    def speak
      return "Fine. Be that way!" if @heard.silence?
      return "Woah, chill out!" if @heard.yelling?
      return "Sure." if @heard.question?
      "Whatever."
    end
  end
end

class WordsHeard
  def initialize(msg)
    @msg = msg.to_s
  end
  
  def question?
    !@msg.match(/.+\?$/).nil?
  end

  def yelling?
    @msg.upcase == @msg
  end

  def silence?
    @msg.empty?
  end
end
