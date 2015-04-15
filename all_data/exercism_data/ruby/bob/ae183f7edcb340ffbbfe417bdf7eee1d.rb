module Exercise
  class Teenager; end

  module Attitudable
    def self.feel_about(msg)
      ([
         Uncaring, 
         Relaxed, 
         Response, 
       ].find {|act| act.to?(msg)} || Nothing)
        .new
        .msg
    end  
  end

  module Matchable
    def to?(msg)
      patterns.inject(false) {|applies, pattern| applies ||= msg =~ pattern}
    end    
  end

  class Nothing
    def msg
      "Fine. Be that way."
    end
  end

  class Response
    @patterns = [
                  /.+\?$/, 
                  /^Does.+\?$/
                ]

    class << self
      attr_reader :patterns
      include Matchable
    end

    def msg
      "Sure."
    end
  end

  class Uncaring
    @patterns = [
                  /^Let's.+!$/,
                  /-/,  
                  /.+\.$/
                ]

    class << self
      attr_reader :patterns  
      include Matchable
    end
 
    def msg
      "Whatever."
    end     
  end

  class Relaxed
    @patterns = [
                  /[1].+[2].+[3].+!$/,
                  /[A-Z1-9]+[^%\^*@#$(*\^]*[A-Z1-9]+!$/,
                  /[A-Z]+[^?]$/
                 ]

    class << self
      attr_reader :patterns
      include Matchable
    end

    def msg
      "Woah, chill out!"
    end
  end

  class Bob
    def hey(msg)
      attitudable.feel_about(msg)
    end

    def attitudable
      @attitudable ||= Attitudable
    end
  end
end
