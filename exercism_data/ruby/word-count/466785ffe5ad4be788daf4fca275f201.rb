class Phrase < String

  def word_count
    word_count = {}
    self.sanitize_and_regularize.split.each do |key|
      if word_count.has_key?(key)
        word_count[key] += 1
      else
        word_count[key] = 1
      end 
    end
    word_count
  end

  def sanitize_and_regularize
    gsub(%r{[/\;!&+=?~@$%^,:]},'').downcase
  end

end
