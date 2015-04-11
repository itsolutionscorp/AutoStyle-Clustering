class Bob
  def hey(remark)
    if (remark =~ /[A-Z]{4}|([A-Z]{1}!)/) != nil 
      p "Whoa, chill out!"
    elsif remark.end_with? "?"
      p "Sure."
    elsif remark == '' || remark.strip.empty?
      p "Fine. Be that way!"
    else
      p "Whatever."
    end
  end
end

bobby = Bob.new
bobby.hey("Tom-ay-to, tom-aaaah-to.")

# p ' ' * rand(1..10)
