#!/usr/bin/env ruby

class Bob

        def hey(msg)
                case
                when silent?(msg) then 'Fine. Be that way!'
                when yelling?(msg) then 'Woah, chill out!'
                when question?(msg) then 'Sure.'
                else
                        'Whatever.'
                end
        end

        private
        def silent?(str)
                return true if str.to_s.strip == ''
        end
        def question?(str)
                return true if str.to_s.end_with?('?')
        end
        def yelling?(str)
                return true if str == str.upcase
        end

end
