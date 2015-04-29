class Bob

  def hey(remark)
    if remark == remark.upcase && remark.downcase != remark
      "Whoa, chill out!"
    elsif
      remark.include?('?')
      "Sure."
    elsif
      remark.strip.empty?
      "Fine, be that way!"
    else
    "Whatever."
    end
  end

end
