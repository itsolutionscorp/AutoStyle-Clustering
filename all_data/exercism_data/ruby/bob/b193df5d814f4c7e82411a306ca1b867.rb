require 'pry-byebug'

class Bob
  def hey(remark)
    @remark = remark
    if yelling?
      "Whoa, chill out!"
    elsif question?
      "Sure."
    elsif silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  
  def yelling?
    true if (@remark =~ /[A-Z]{4}/) != nil || (@remark =~ /[A-Z]{2}+!/) != nil
  end

  def question?
     true if @remark.end_with?("?")
  end

  def silence?
    true if @remark.strip.empty?  
  end
end
