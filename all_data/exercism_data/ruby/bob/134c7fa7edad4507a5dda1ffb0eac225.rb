class Bob

  def hey(statement)
    @statement = Statement.new(statement)
    @statement.output
  end

end

class Statement

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def output
    if all_caps?
      "Woah, chill out!"
    elsif question?
      "Sure."
    elsif silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def all_caps?
   return false if all_numbers?

   capitalization_check = text.gsub(" ", "").chars.map { |c| c.upcase == c }.uniq

   if capitalization_check.count > 1
     false
   else
     capitalization_check.first
   end
  end

  def question?
    text.end_with?("?")
  end

  def silent?
    no_text? or all_spaces?
  end

  def all_numbers?
    characters = text.split(", ")
    cleaned_up_characters = characters.map { |l| l.gsub("?", "") }
    cleaned_up_characters.each { |c| Integer(c) }
    true
  rescue
    false
  end

  def no_text?
    text.empty?
  end

  def all_spaces?
    text.chars.map { |c| c == " " }.uniq == [true]
  end
end
