#!/usr/bin/ruby

class Bob
  def initialize(appreciator = VoiceAppreciator.new)
    @appreciator = appreciator
  end

  def hey(message)
    return 'Fine. Be that way!' if @appreciator.silence? message
    return 'Whoa, chill out!' if @appreciator.yelling? message
    return 'Sure.' if @appreciator.question? message
    'Whatever.'
  end
end

class VoiceAppreciator
  def silence?(message)
    message.strip.empty?
  end

  def yelling?(message)
    upcase?(message) and letters?(message)
  end

  def question?(message)
    message.end_with? '?'
  end

  private

  def upcase?(message)
    message.upcase == message
  end

  def letters?(message)
    /[a-zA-Z]/.match(message)
  end
end
