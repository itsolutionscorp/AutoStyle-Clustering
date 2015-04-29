class Bob

  attr_accessor :remark

  def hey(remark)
    @remark = remark
    @bob_says = {
      question: "Sure.",
      all_caps: "Whoa, chill out!", 
      empty:    "Fine. Be that way!",
      other:    "Whatever."
      }
    @bob_says.fetch(type_input)
  end

  def type_input 
    @remark = remark.strip

    check = remark.scan(/[1234567890?]/)   # check will be only the numbers and ? of 'remark'

    if remark.length == 0 
      :empty  
    elsif remark.end_with?("?") && remark.length == check.join.length
      :question
    elsif remark.end_with?("?") && remark == remark.upcase
      :all_caps
    elsif remark.end_with?("?")
      :question
    elsif remark.upcase == remark.downcase
      :other
    elsif remark == remark.upcase
      :all_caps
    else
      :other
    end
  end
end
