swallow = [
  'She swallowed the cow to catch the goat.',
  'She swallowed the goat to catch the dog.',
  'She swallowed the dog to catch the cat.',
  'She swallowed the cat to catch the bird.',
  'She swallowed the bird to catch the spider that wriggled'\
  ' and jiggled and tickled inside her.',
  'She swallowed the spider to catch the fly.'
  ]

def verse(animal, &block)
  puts '' if animal != 'fly'
  puts "I know an old lady who swallowed a #{animal}"
  block.call
  puts "I don't know why she swallowed the fly. Perhaps she'll die."
end

verse('fly') do
end

verse('spider') do
  puts 'It wriggled and jiggled and tickled inside her.'
  puts swallow[5]

end

verse('bird') do
  puts 'How absurd to swallow a bird!'
  puts "#{swallow[4]}\n#{swallow[5]}"
end

verse('cat') do
  puts 'Imagine that, to swallow a cat!'
  puts "#{swallow[3]}\n#{swallow[4]}\n#{swallow[5]}"
end

verse('dog') do
  puts 'What a hog, to swallow a dog!'
  puts "#{swallow[2]}\n#{swallow[3]}\n#{swallow[4]}\n#{swallow[5]}"
end

verse('goat') do
  puts 'Just opened her throat and swallowed a goat!'
  puts "#{swallow[1]}\n#{swallow[2]}\n#{swallow[3]}" +
  "\n#{swallow[4]}\n#{swallow[5]}"
end

verse('cow') do
  puts "I don't know how she swallowed a cow!"
  puts "#{swallow[0]}\n#{swallow[1]}\n#{swallow[2]}\n#{swallow[3]}" +
  "\n#{swallow[4]}\n#{swallow[5]}"
end

puts 'I know an old lady who swallowed a horse.'
puts "She's dead, of course!"
