class Bob

  attr_writer :remark
  attr_reader :remark

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

    if remark.length == 0 
      :empty
    elsif remark.end_with?("?") && remark.downcase == remark.upcase
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

# a = Bob.new
# p a.hey("     \t ")
