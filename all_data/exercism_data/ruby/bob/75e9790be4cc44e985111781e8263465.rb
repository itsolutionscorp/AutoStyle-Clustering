require 'delegate'

class Person
  class << self
    attr_accessor :responses
  end
  self.responses = {}

  def self.respond_to(type, &with)
    responses[type] = with
  end

  def self.response_types
    responses.keys - [:default]
  end

  def self.inherited(subclass)
    subclass.responses = self.responses.dup
  end

  def response_for(message)
    type = self.class.response_types.find { |t| message.send(:"#{t}?") }
    type ||= :default
    respond_with = self.class.responses.fetch(type)
    respond_with.call
  end

  def hey(msg)
    message = MessageAnalyzer.new(msg.to_s)
    response_for(message)
  end
end

class Bob < Person
  respond_to(:nothing_said) { 'Fine. Be that way.' }
  respond_to(:yelling) { 'Woah, chill out!' }
  respond_to(:question) { 'Sure.' }
  respond_to(:default) { 'Whatever.' }
end

class MessageAnalyzer < DelegateClass(String)
  def nothing_said?
    empty?
  end

  def question?
    match(/\?\Z/)
  end

  def yelling?
    !match(/[a-z]/)
  end
end
