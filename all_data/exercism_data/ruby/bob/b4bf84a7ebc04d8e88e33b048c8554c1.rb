
class Bob   
    def hey(remark)
        remark.gsub!(/\n/, '_')
        return 'Whoa, chill out!' if matches_all_caps(remark)
        return 'Sure.' if matches_a_question(remark)
        return 'Fine. Be that way!' if matches_silence(remark)
        'Whatever.'
    end
    
    def matches_all_caps(remark)
        upper = remark.match(/[A-Z]/m) != nil
        no_lower = remark.match(/[a-z]/m) == nil
        upper && no_lower
    end
    
    def matches_a_question(remark)
        remark.match(/\?$/m) != nil
    end
    
    def matches_silence(remark)
        remark.match(/^\s*$/) != nil
    end
end
