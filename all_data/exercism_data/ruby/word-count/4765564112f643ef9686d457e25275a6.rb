class Phrase < String

  def word_count
    words.each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

  def words
  	downcase.scan(%r{\w+})
  end

end
