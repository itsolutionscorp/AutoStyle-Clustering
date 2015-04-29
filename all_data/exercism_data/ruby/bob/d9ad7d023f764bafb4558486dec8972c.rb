class Bob

  def hey(remark)
    @remark = remark

    if remark == remark.upcase && contains_alphabet_character? && !remark.strip.empty?
      'Whoa, chill out!'
    elsif remark[-1] == "?"
      'Sure.'
    elsif remark.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end    
  end

  def contains_alphabet_character?
    true if !(@remark =~ /\p{Alpha}/).nil?
  end

  # def only_numeric?
  #   Float(@remark.gsub(/[^A-Za-z0-9]/i, '')) != nil rescue false
  # end
  
end
