class Acronym
  def self.abbreviate(text)
    text = delimit_on_colon(text)
    text = relevant_letters(text)

    text.upcase
  end

  def self.delimit_on_colon(text)
    colon_index = text.chars.find_index(":")

    colon_index ? text.slice(0, colon_index) : text
  end

  def self.relevant_letters(text)
    count = 0
    relevant = ''

    while count < text.size
      case text[count]
      when " ", "-"
        relevant << text[count + 1]
        count += 2
      when ("A".."Z")
        relevant << text[count]
        count += 1
      else
        count += 1
      end
    end

    relevant
  end
end
