class Bob
  def hey(input)
    answer(input)
  end

  private
    
    def answer(input)
      if input.to_s.empty?
        return "Fine. Be that way."
      elsif input.end_with?("?")
        return "Sure."
      elsif input == input.upcase
        return "Woah, chill out!"
      else
        return "Whatever."
      end
    end

end
