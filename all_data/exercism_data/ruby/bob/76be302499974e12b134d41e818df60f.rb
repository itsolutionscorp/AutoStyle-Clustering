 class Bob
    def initialize
    end

    def hey(msg)
      if msg =~ /^[A-Z]+.+[A-Z]$/ || msg =~ /^\d.+!/ || msg =~  /^[A-Z].+[A-Z]!/ || msg =~  /.+[^\w]+[A-Z]!/ || msg =~ /^[A-Z]{2,}.+[A-Z]\?/
          'Woah, chill out!'
      elsif msg =~ /.+\?.+\./ || msg =~ /\n/
          "Whatever."
     elsif msg =~ /.+\?/ || /.+[\?!\.].+$/ || msg =~ /.+[!\?\/].+[\.\?!]/
          "Sure."
      elsif msg =~ /.+\?.+/ || msg =~ /^\d.+\d$/  || msg =~ /[A-Z]{2,}/ || msg =~  /.+[a-z]!/ || msg =~ /.+[.!?].+\./
          'Whatever.'
      elsif msg =~ /^    $/ || msg =~ /^$/
          'Fine. Be that way!'
      else
          'Whatever.'
      end
    end
  end
