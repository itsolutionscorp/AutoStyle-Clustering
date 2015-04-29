class Words

  attr_reader :input_string
  def initialize(input_string)
    @input_string = input_string.downcase.gsub(/\W/, ' ').split(' ')
  end

  def count
    input_string.each_with_object({}) do |word, hash|
      hash[word] = input_string.count(word)
    end
  end
end     
