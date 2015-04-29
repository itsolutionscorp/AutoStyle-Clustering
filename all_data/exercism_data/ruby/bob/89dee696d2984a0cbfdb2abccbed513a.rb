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
    @remark =~ /[A-Z]/ && @remark.upcase == @remark
  end

  def question?
     @remark.end_with?("?")
  end

  def silence?
    @remark.strip.empty?  
  end
end
