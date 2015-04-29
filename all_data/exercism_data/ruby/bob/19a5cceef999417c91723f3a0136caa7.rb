#!/usr/bin/env ruby -w

module Visitable
  def accept visitor
    visitor.visit self
  end
end

class Object
  include Visitable
end

class Visitor
  def self.visitor_for *klasses, &block
    klasses.each do |klass|
      define_method(:"visit_#{klass.name}", block)
    end
  end

  def visit thing
    thing.class.ancestors.each do |ancestor|
      method_name = :"visit_#{ancestor.name}"
      next unless respond_to? method_name
      return send method_name, thing
    end

    raise "Can't handle #{thing.class}"
  end
end

class Statement < Struct.new(:body); end
class Question < Statement; end
class Silence < Statement; end
class Yell < Statement; end

class StatementClassifier
  def classify(it)
    case
    when silence?(it)
      return Silence.new(it)
    when yell?(it)
      return Yell.new(it)
    when question?(it)
      return Question.new(it)
    end
    Statement.new(it)
  end

  def question?(it)
    it.end_with?('?')
  end

  def silence?(it)
    it.strip.empty?
  end

  def yell?(it)
    it =~ /[A-Z]/ && it !~ /[a-z]/
  end
end

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
