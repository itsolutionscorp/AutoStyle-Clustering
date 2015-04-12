class Phrase < String

  def word_count
    word_count = {}
    word_array.each do |key|
      word_count.has_key?(key) ? word_count[key] += 1 : word_count[key] = 1
    end
    word_count
  end

  def word_array
    sanitize_and_regularize.split
  end

  def sanitize_and_regularize
    gsub(%r{[/\;!&+=?~@$%^,:]},'').downcase
  end

end
