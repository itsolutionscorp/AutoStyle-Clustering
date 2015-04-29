class Phrase < String

  def word_count
    word_count = word_array.inject(Hash.new(0)) do |result, key| 
      result[key] += 1
      result
    end
  end

  def word_array
    sanitize_and_regularize.split
  end

  def sanitize_and_regularize
    gsub(%r{[/\;!&+=?~@$%^,:]},'').downcase
  end

end
