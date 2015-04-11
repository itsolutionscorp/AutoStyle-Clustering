class Bob
  def initialize
  end

  def hey(remark)
    if remark.to_s.strip == ""
      return 'Fine. Be that way!'

    elsif self.contains_letters(remark)
      if remark[-1] == "?" && remark.upcase != remark
        return 'Sure.'
      elsif remark.upcase == remark
        return "Whoa, chill out!"
      else
        return "Whatever."
      end

    elsif remark[-1] == "?"
      return "Sure."
    else
      return "Whatever."
    end
  end

  def contains_letters(remark)
    letters = ('a'..'z').to_a
    all_letters = remark.downcase.split("").select {
      |chr| letters.include?(chr)
    }

    all_letters.length > 0
  end
end
