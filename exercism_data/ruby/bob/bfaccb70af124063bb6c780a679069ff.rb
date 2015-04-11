class TeenagerData
  def self.data
    <<-eos
    {
      "teenagers": [
        {
          "name": "Bob",
          "responses": [
            {
              "message_type": "shouting",
              "response": "Woah, chill out!"
            },
            {
              "message_type": "question",
              "response": "Sure."
            },
            {
              "message_type": "silent",
              "response": "Fine. Be that way!"
            }
          ],
          "default_response": "Whatever."
        },
        {
          "name": "Doge",
          "responses": [
            {
              "message_type": "shouting",
              "response": "so yell, much loud"
            },
            {
              "message_type": "question",
              "response": "wow, good ask"
            },
            {
              "message_type": "silent",
              "response": "amaze, many quiet"
            }
          ],
          "default_response": "wow"
        }
      ]
    }
    eos
  end
end

class Response
  attr_reader :message_type, :response
  def initialize(message_type:, response:)
    @message_type = message_type.to_sym
    @response = response
  end

  def to_s
    response
  end
end

require 'json'
require 'singleton'
class TeenagerConfig
  include Singleton

  def initialize
    @teenagers = JSON.parse(TeenagerData.data, symbolize_names: true)[:teenagers]
  end

  def self.responses_for(name)
    instance.responses_for(name)
  end

  def responses_for(name)
    hash_responses_for(name).reduce([]) do |responses, hash_response|
      responses << Response.new(hash_response)
    end
  end

  def self.default_response_for(name)
    instance.default_response_for(name)
  end

  def default_response_for(name)
    teenager_for(name)[:default_response]
  end

  def self.load_classes
    instance.load_classes
  end

  def load_classes
    teenagers.each do |teenager|
      Object.const_set(teenager[:name], Class.new(Teenager))
    end
  end

  private

  attr_reader :teenagers

  def teenager_for(name)
    teenagers.find do |teenager|
      teenager[:name] == name
    end
  end

  def hash_responses_for(name)
    teenager_for(name)[:responses]
  end
end

class Teenager
  def initialize
    @responder = Responder.new(responses, default: default_response)
  end

  def hey(message)
    @responder.respond_to(message)
  end

  private

  def default_response
    @default_response ||= TeenagerConfig.default_response_for(name)
  end

  def responses
    @responses ||= TeenagerConfig.responses_for(name)
  end

  def name
    self.class.to_s
  end
end

TeenagerConfig.load_classes

class Responder
  def initialize(responses, default:)
    @responses = responses
    @default = default
  end

  def respond_to(message)
    response_for(message_type(message)).to_s
  end

  private

  attr_reader :responses, :default

  def response_for(message_type)
    responses.find(-> { default }) do |response|
      response.message_type == message_type
    end
  end

  def message_types
    responses.map(&:message_type)
  end

  def message_type(message)
    message_types.find do |message_type|
      Message.new(message).is?(message_type)
    end
  end
end

module SymbolExtensions
  refine Symbol do
    def to_predicate
      (to_s << "?").to_sym
    end
  end
end

require 'delegate'
class Message < SimpleDelegator
  using SymbolExtensions

  def is?(message_type)
    send(message_type.to_predicate)
  end

  def shouting?
    upcase == self && downcase != self
  end

  def question?
    end_with?('?')
  end

  def silent?
    strip.empty?
  end
end
