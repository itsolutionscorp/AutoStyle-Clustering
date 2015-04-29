class Acronym

  def self.abbreviate_one_word(word)

      # If word is all-caps or all-lowercase, take the first letter (as uppercase)
      if word == word.upcase || word == word.downcase
        return word[0].upcase
      end

      # Mixed case words - always take the first letter regardless of its case,
      # plus all other uppercase letters.
      word[0].upcase + word[1..-1].scan(/\p{Upper}/).join("")

  end

  private_class_method :abbreviate_one_word

  def self.abbreviate(long_name)
    long_name.split(/[^\w]+/).reduce("") do |acronym, word|
      acronym + abbreviate_one_word(word)
    end
  end

end
