class Phrase < String

  def word_count
    downcase.scan(%r{\w+}).each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

end
