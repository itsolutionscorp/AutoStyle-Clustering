class Acronym

  def self.abbreviate(str)
    result = ''
    str.split(/[\s-]/).each do |x|
      if x.include?":"
        result += x[0]
      else
        result += x[0].upcase
        result += /[A-Z]/.match(x[1..-1]).to_s 
      end
    end
    return result
  end

end
