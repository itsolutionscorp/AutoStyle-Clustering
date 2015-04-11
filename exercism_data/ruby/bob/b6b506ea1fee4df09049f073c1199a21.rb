puts 'bob is a lacsidasical teenager, you can either ask him a question, tell him something,'
puts 'yell at him or completely ignore him'
user_input = gets.chomp
if user_input.include? "?"
  puts "Sure"
elsif user_input.include? "!"
  puts "Whatever."
elsif user_input.length == 0
  puts "Fine. Be that way!"
end
unless user_input == user_input.downcase
  puts "Woah, chill out!"
end
