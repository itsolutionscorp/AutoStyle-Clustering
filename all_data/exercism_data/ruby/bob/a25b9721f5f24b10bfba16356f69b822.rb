#!/usr/bin/env ruby -w

$LOAD_PATH << File.dirname(__FILE__)
require 'visitor'
require 'classification'

class Bob
  attr_reader :classifier
  attr_reader :responder

  def initialize(classifier = StatementClassifier.new, responder = Responder.new)
    @classifier = classifier
    @responder = responder
  end

  def hey(statement)
    respond(classify(statement))
  end

  private

  def classify(statement)
    classifier.classify(statement)
  end

  def respond(statement)
    statement.accept(responder)
  end

  class Responder < Visitor
    visitor_for Statement do |_|
      'Whatever.'
    end

    visitor_for Question do |_|
      'Sure.'
    end

    visitor_for Yell do |_|
      'Woah, chill out!'
    end

    visitor_for Silence do |_|
      'Fine. Be that way!'
    end
  end
end
