#!/usr/bin/env ruby

# Exercism 11
# Word-Count
# Return a hash of word+count

class Phrase

  def initialize(word)
    @@words = word
  end

  def word_count
    Hash[
      @@words.scan(/[a-zA-Z0-9']+/)
      .group_by{|word| word.downcase}
      .map{|word, words|[word, words.size]}
    ]
  end

end
