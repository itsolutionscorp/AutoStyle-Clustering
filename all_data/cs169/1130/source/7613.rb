def palindrome?(string) 
	string = string.gsub(/\W/,'')
	return string.downcase.reverse == string.downcase
end

def count_words(string)
	sarr   = string.split
	h = Hash.new

	sarr.each do |candidate|
		candidate = candidate.gsub(/\W/,'')
		if candidate.length == 0 then next end
		h.has_key?(candidate.downcase) ? h[candidate.downcase] += 1 : h[candidate.downcase] = 1
	end
	return h
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end
 
def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  
  strategies = ["p","r","s"]
  if not (strategies.include?(game[0][1].downcase) and strategies.include?(game[1][1].downcase))
  	raise NoSuchStrategyError
  end 

  if game[0][1].downcase == "p"
  	if game[1][1].downcase == "p" or game[1][1].downcase == "r" then return game[0]
  	else return game[1]
  	end
  elsif game[0][1].downcase == "r"
  	if game[1][1].downcase == "r" or game[1][1].downcase == "s" then return game[0]
  	else return game[1]
  	end
  else
  	if game[1][1].downcase == "s" or game[1][1].downcase == "p" then return game[0]
  	else return game[1]
  	end
  end
end

def rps_tournament_winner(game)
	player_one = game[0]
	player_two = game[1]
	if player_one[0].class == Array then player_one =  rps_tournament_winner(player_one) end
	if player_two[0].class == Array then player_two =  rps_tournament_winner(player_two) end

	return rps_game_winner([player_one,player_two])
end

def combine_anagrams(words)
	res_arr = Array.new
  	exist_arr = Array.new
  	for i in 1.upto(words.length) do exist_arr.push(0) end

  	for i in 1.upto(words.length)
  		if not exist_arr[i-1] == 0 then next end

  		first = words[i-1].downcase.split(//).sort
  		res_arr.push([words[i-1]])
  	
  		for j in (i+1).upto(words.length)
  			if not exist_arr[j-1] == 0 then next end
  			
  			second = words[j-1].downcase.split(//).sort
  			if first == second
  				exist_arr[j-1] = 1
  				res_arr[-1].push(words[j-1])
  			end
  		end
  	end

  	return res_arr
end