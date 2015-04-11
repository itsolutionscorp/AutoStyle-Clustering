class Bob
  def hey(str)
    return 'Sure.' if str.end_with?('?') and (calm?(str) or only_numbers?(str))
    return 'Fine. Be that way!' if str.strip.empty?
    return 'Whoa, chill out!' if not calm?(str) and not only_numbers?(str)
    'Whatever.'
  end

  private

    def calm?(str)
      str.gsub(/[\W\d]/,'').chars.any? { |l| /[a-z]/.match(l)  }
    end

    def only_numbers?(str)
      str.gsub(/[\W]/,'').chars.all? { |n| n =~ /[0-9]/  }
    end

end
