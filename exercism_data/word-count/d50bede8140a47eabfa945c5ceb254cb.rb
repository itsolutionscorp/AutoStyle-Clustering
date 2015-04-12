class Phrase
  attr_accessor :phrase

  def initialize(input)
    @phrase = input
  end

  def word_count
    @hash         = Hash.new 0

    array_of_words.group_by{ |word| word.match(/[[:word:]]*/).to_s.downcase }.
                    each do |key, value|
                      @hash[key] = value.count unless key == ""
                    end

    @hash
  end

  private
  def array_of_words
    @phrase.gsub(",", ", ").split
  end
end
