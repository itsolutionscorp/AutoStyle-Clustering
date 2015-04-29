class AnagramStats
  attr_accessor :words, :char_counts, :combined

  def initialize(words)
    @words = words
  end

  def combine
    self.char_counts = get_char_counts
    self.combined = words.inject(Array.new) do |memo, k|
      v = char_counts[k]
      if memo.empty?
        memo << [k]
      else
        added_to = memo.inject(nil) do |found, arr|
          if compare_words(v, char_counts[arr.first])
            arr << k
            found = k
            break found
          end
        end
        memo << [k] unless added_to
      end
      memo
    end
  end

  def get_char_counts
    words.inject(Hash.new) {|memo,w| memo[w] = char_count(w); memo}
  end

  def compare_words(wc1, wc2)
    return false unless wc1.keys.length == wc2.keys.length
    wc1.each do |k,v|
      return false unless v == wc2[k]
    end
    return true
  end

  def char_count(word)
    word.chars.inject(Hash.new) {|memo, c| memo[c.downcase] = (memo[c.downcase] || 0) + 1; memo}
  end
end

def combine_anagrams(words)
  AnagramStats.new(words).combine
end
