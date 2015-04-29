class Words

  attr_reader :split_cleaned_input
  def initialize(raw_input)
    @split_cleaned_input = spit_and_clean_input(raw_input)
  end

  def count
    split_cleaned_input.each_with_object({}) do |word, hash|
      hash[word] = split_cleaned_input.count(word)
    end
  end

  def spit_and_clean_input(raw_input)
    raw_input.downcase.gsub(/\W/, ' ').split(' ')
  end
end     
