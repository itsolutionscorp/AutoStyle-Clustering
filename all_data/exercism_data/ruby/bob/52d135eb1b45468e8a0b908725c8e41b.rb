bob = Object.new

def bob.hey something
  if something.empty?
    puts "Fine, be that way."
  elsif something.upcase == something
    puts "Woah, chill out!"
  elsif something.end_with? "?"
    puts "Sure!"
  else
    puts "Yeah, whatever."
  end
end

bob.hey "Do you like nachos?"
bob.hey "Want to go to the movies?"
bob.hey "Go suck an egg."
bob.hey "You stink!"
bob.hey "Want some chocolate?"
bob.hey "God, this weather is awful."
bob.hey "Let's go make out behind the gym!"
bob.hey "THE ZOMBIES ARE COMING!!!11!!"
bob.hey "This song is soooo good."
bob.hey "Does this cryogenic chamber make me look fat?"
bob.hey "WATCH OUT!"
bob.hey "Tom-ay-to, tom-aaah-to."
bob.hey ""

loop { bob.hey gets.chomp }
