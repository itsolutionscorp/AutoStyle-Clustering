#!/usr/bin/env ruby

class Bob
  def hey(remark)
    remark.strip!

    if remark.empty?
      'Fine. Be that way!'
    elsif remark !~ /[a-z]/ && remark =~ /[A-Z]/
      'Whoa, chill out!'
    elsif remark[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
