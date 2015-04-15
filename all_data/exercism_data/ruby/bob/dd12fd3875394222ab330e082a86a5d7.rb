module Statement
  refine String do
    def antagonistic?
      self.upcase == self && self =~ /[[:alpha:]]/
    end
    def silent?
      self.strip.empty?
    end
  end
end

using Statement

class Bob
  def hey(statement)
    if statement.silent?
      "Fine. Be that way!"
    elsif statement.antagonistic?
      "Woah, chill out!"
    elsif statement.end_with? '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
