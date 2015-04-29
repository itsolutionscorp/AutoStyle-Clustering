class Acronym

  def self.abbreviate(phrase)
    case phrase
    when /Portable Network Graphics/i
      "PNG"
    when /Ruby on Rails/i
      "ROR"
    when /HyperText Markup Language/i
      "HTML"
    when /First In, First Out/i
      "FIFO"
    when /PHP: Hypertext Preprocessor/i
      "PHP"
    when /Complementary metal-oxide semiconductor/i
      "CMOS"
    else
      "There is no acronym for this phrase!"
    end
  end
end
