class Phrase < String

  def word_count
    word_count = Hash.new(0)
    word_array.each do |key|
      word_count[key] += 1
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
