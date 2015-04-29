require "pry"
class Proverb
  def initialize(*words, qualifier: nil)
    @sentences = [* build_sentences(words)]
    qualifier = "#{qualifier} " if qualifier
    @sentences << "And all for the want of a #{qualifier}nail."
  end

  def to_s
    @sentences.join("\n")
  end

  private
  def build_sentences(words, current = nil)
    return if words.empty?
    first, sentence = words.shift, []

    if current
      sentence << "For want of a #{current} the #{first} was lost."
    end

    sentence + [* build_sentences(words, first)]
  end
end
