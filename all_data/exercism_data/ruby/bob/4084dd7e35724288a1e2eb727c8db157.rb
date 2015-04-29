class Bob
  def hey(message)
    brain.response_for(message)
  end

  def brain
    @brain ||=
      Brain.new(:default_response => 'Whatever.') do |b|
        respond_with('Woah, chill out!')   { |message| message =~ /[A-Z]/ && message !~ /[a-z]/ }
        respond_with('Sure.')              { |message| message.end_with?('?') }
        respond_with('Fine. Be that way!') { |message| message.strip.empty? }
      end
  end

  class Brain
    def initialize(options = {}, &block)
      @default_response = options.fetch(:default_response)
      @impulses = []
      instance_eval(&block)
    end

    def response_for(message)
      @impulses.each do |(predicate, response)|
        if predicate.call(message)
          return response
        end
      end
      @default_response
    end

    private

    def respond_with(response, &predicate)
      @impulses << [predicate, response]
    end
  end
end
