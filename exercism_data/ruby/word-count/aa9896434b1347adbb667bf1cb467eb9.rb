class Phrase < String
  def word_count
    result = Hash.new 0
    scan(/\w+/) do |word|
        result[word.downcase] += 1
    end
    result
  end
end
