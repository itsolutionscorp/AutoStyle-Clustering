class Bob
  def hey(blurb)
    blurb_array = blurb.split('')
    blurb_nums = blurb.delete(', ')
    letters = ('A'..'z').to_a

    if blurb == " "*blurb.length || blurb =~ /\t/
      'Fine. Be that way!'
    elsif blurb == blurb.upcase && blurb =~ /#{letters.to_s}/ && !(blurb_nums[/[0-9]+/]  == blurb_nums)
      'Whoa, chill out!'
    elsif blurb_array.include?('?') && (blurb[-1,1] == '?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
