#!/usr/bin/ruby

def palindrome?(string)
    s = string.downcase.gsub(/[^a-z]/i, "");
    s == s.reverse
end

#print palindrome?("A man, a plan, a canal -- Panama") #=> true
#print palindrome?("Madam, I'm Adam!") # => true
#print palindrome?("Abracadabra") # => false (nil is also ok)

def count_words(string)
    h = { }
    string.downcase.gsub(/\b[a-z]+\b/).each do |w|
	h[w] = (h[w] || 0 ) + 1
    end
    #print h,"\n"
    return h
end

#count_words("A man, a plan, a canal -- Panama")
# => {'a' => 3, 'man' => 1, 'canal' => 1, 'panama' => 1, 'plan' => 1}
#count_words "Doo bee doo bee doo" # => {'doo' => 3, 'bee' => 2}

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def _rps_game_winner(game)
    raise WrongNumberOfPlayersError unless game.length == 2
    a = ""
    game.each do |k,v|
	v = v.downcase
	if (v != "p" && v!="s" && v!="r") then
	    raise NoSuchStrategyError
	end
	a = a + v
    end
    if a == "ss" || a=="pp" || a=="rr" then
	return game[0]
    end
    if a == "sp" || a=="pr" || a=="rs" then
	return game[0]
    end
    return game[1]
end
def rps_game_winner(game)
    return _rps_game_winner(game)
end
#print rps_game_winner([ ["Armando", "P"], ["Dave", "S"] ]),"\n"

def rps_tournament_winner(tour)
    if tour[0][0].is_a? String then
	w = _rps_game_winner(tour)
	print "Got ",w,"\n"
	return w
	v = tour[ w == tour[0][0] ? 0 : 1 ][1]
	print "Got "+w+" "+v+"\n"
	return [w,v]
    end
    tn = [ ]
    tour.each do |t|
	tn += [ rps_tournament_winner(t) ]
    end
    print "tn = ",tn,"\n"
    return _rps_game_winner(tn)
end
if nil then
  print rps_tournament_winner(
  [
    [
	[ ["Armando", "P"], ["Dave", "S"] ],
	[ ["Richard", "R"], ["Michael", "S"] ],
    ],
    [
	[ ["Allen", "S"], ["Omer", "P"] ],
	[ ["David E.", "R"], ["Richard X.", "P"] ]
    ]
  ]
  ),"\n"
end

def combine_anagrams(words)
    res = [ ]
    words.each do |w|
#	print "res = ",res.join(" "),"; ","w=",w,"\n"
	pas = 0
	res.each do |r|
#	    print r[0].downcase," ",r[0].downcase.split('').sort.join == w.downcase.split('').sort.join," ",w.downcase,"\n"
	    if r[0].downcase.split('').sort.join == w.downcase.split('').sort.join then
		r << w
		print r.join(":"),"\n";
		pas = 1
		break
	    end
	end
	if pas == 0 then
#	    print res,"; new ",[w],"\n"
	    res << [w]
	end
    end
    res
end
combine_anagrams(['creams','cars', 'for', 'potatoes', 'racs', 'four','scar',  'scream'])
#.join(";"),"\n"

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

