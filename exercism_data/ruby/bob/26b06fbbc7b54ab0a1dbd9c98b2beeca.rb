# Bob responds to four cases
class Bob
    def initialize
        @tests = {
            -> (x){ x.empty? } => 'Fine. Be that way!',
            ->(x){ x.upcase == x } => 'Woah, chill out!',
            ->(x){ x[-1, 1] == '?' } => 'Sure.',
            ->(x){ true } => 'Whatever.'
        }
    end

    def hey (s)
        @tests.each_key { |x| return @tests[x] if x.call s }
    end
end
