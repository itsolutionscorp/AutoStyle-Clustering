class Bob

  # The order of the matchers is important here
  MATCHERS = {

    :shouting =>
      {
        :patterns => [ /\A([^a-z]|[0-9]|\W)+\z/x, /[A-Z]+/ ],
        :response => "Whoa, chill out!"
      },

    :silence  => { :patterns => [ /\A^\s*\z/m ], :response => "Fine. Be that way!" },
    :question => { :patterns => [ /.*[?]\z/m ],  :response => "Sure."              },
    :default  => { :patterns => [ /.*/m ],       :response => "Whatever."          }
  }

  def hey(text)
    MATCHERS.detect do |key, opts|
      opts[:patterns].all? { |pattern| text =~ pattern }
    end.last[:response]
  end
end
