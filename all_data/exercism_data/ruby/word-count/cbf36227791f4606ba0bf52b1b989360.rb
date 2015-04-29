require 'forwardable'

class Phrase
  def initialize(content)
    @content = content
  end

  def word_count
    counter.to_hash
  end

  def to_str
    @content
  end

  private

  def words
    @words ||= Words.new self
  end

  def counter
    @counter ||= Counter.new words
  end

end

class Words
  extend Forwardable
  include Enumerable

  def_delegator :split, :each

  def initialize(phrase)
    @phrase = phrase.to_str
  end

  private
  def split
    @phrase.scan /\w+/
  end

end

class Counter
  extend Forwardable

  def_delegator :register, :to_hash

  def initialize(words)
    words.each do |word|
      register.add word
    end
  end

  private
  def register
    @register ||= Register.new
  end

end

class Register

  def initialize
    @counts = {}
  end

  def add(entry)
    entry.downcase!
    counts[entry] ||= 0
    counts[entry] += 1
  end

  def to_hash
    counts
  end

  private
  attr_accessor :counts

end
