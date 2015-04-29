require 'pry-byebug'

class Bob
  def hey(remark)
    @remark = remark
    yelling?
  end

  private
  
  def yelling?
    if (@remark =~ /[A-Z]{4}/) != nil || (@remark =~ /[A-Z]{2}+!/) != nil
    "Whoa, chill out!"
    else
      question?
    end
  end

  def question?
    if @remark.end_with?("?")
      "Sure."
    else
      silence?
    end
  end

  def silence?
    if @remark.strip.empty?  
      "Fine. Be that way!"
    else
      everything_else
    end
  end

  def everything_else
    "Whatever."
  end
end
