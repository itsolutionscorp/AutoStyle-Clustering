class Phrase
  attr_reader :input_components
  def initialize(input)
    @input_components = split_and_normalize(input)
  end

  def word_count
    word_map = Hash.new{|h,k| h[k] = 0}

    input_components.inject(word_map) do |map, word|
      clean_word = clean(word)
      next map if clean_word.empty?
      map[clean_word] += 1
      map
    end
  end

  def clean(word)
    word.gsub(/\W/, "")
  end

  def split_and_normalize(input)
    input.split(/\s|,/).map(&:downcase)
  end
end
