# Code heavily borrowed from 'bob_tadassce.rb'.

 class Bob
  attr_accessor :msg
    def initialize
    end

    def hey(msg)
      self.msg = msg

      case
      when question? then "Sure."
      when yell? then "Woah, chill out!"
      when silence? then "Fine. Be that way!"
      else  "Whatever."
      end
    end

    def yell?
      # Added '&& msg =~ /[A-Za-z]/' because numbers test was failing.
      !silence? && msg.upcase == msg && msg =~ /[A-Za-z]/
    end

    def question?
      !yell? && msg.end_with?('?')
    end

    def silence?
      msg.strip.empty?
    end


  end


  # Old hey method

  # if msg =~ /^[A-Z]+.+[A-Z]$/ || msg =~ /^\d.+!/ || msg =~  /^[A-Z].+[A-Z]!/ || msg =~  /.+[^\w]+[A-Z]!/ || msg =~ /^[A-Z]{2,}.+[A-Z]\?/
  #         'Woah, chill out!'
  #     elsif msg =~ /.+\?.+\./ || msg =~ /\n/
  #         "Whatever."
  #    elsif msg =~ /.+\?/ || /.+[\?!\.].+$/ || msg =~ /.+[!\?\/].+[\.\?!]/
  #         "Sure."
  #     elsif msg =~ /.+\?.+/ || msg =~ /^\d.+\d$/  || msg =~ /[A-Z]{2,}/ || msg =~  /.+[a-z]!/ || msg =~ /.+[.!?].+\./
  #         'Whatever.'
  #     elsif msg =~ /^    $/ || msg =~ /^$/
  #         'Fine. Be that way!'
  #     else
  #         'Whatever.'
  #     end
