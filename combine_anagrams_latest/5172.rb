def combine_anagrams(words)
  original=words.sort_by {|x| x.length}
  mywords=[]
  hashi=Hash.new()
  original.each do |obj|
    mywords << obj.downcase.split('').sort.join
  end
  mywords.each do |obj|
    indexes=[]
    for ss in 0...mywords.length
      if obj.eql?mywords[ss] then
        indexes << original[ss]
      end
    end
    hashi[obj]=indexes
  end
  return hashi.values
end




