def combine_anagrams(words) 
	a = Array.new
	if words==[]
		a = []
	else
		s = Array[words[0].to_s]
		a=Array[s]
		for k in 1..(words.length-1)
			found = false	
			for i in 0..(a.length-1)
            	if a[i].kind_of?(Array) 
            		for l in 0..(a[i].length-1)				
					s1=a[i][l].to_s
					#s1= a[i][l]
					if s1.length>1
						s1= s1.downcase.split(//)
						s1= s1.sort.join
					else
						s1=s1.downcase
					end
					#s1= s1.split(//)
					#s1= s1.sort.join.downcase
					s2=words[k].to_s
					if s2.length>1
						s2= s2.downcase.split(//)
						s2= s2.sort.join
					else
						s2=s2.downcase
					end
					if (s1==s2)
						a[i].push(words[k].to_s)
						found = true
					end
                	break if found
				end
				else

					s1=a[i].to_s
					if s1.length>1
						s1= s1.downcase.split(//)
						s1= s1.sort.join
					else
						s1=s1.downcase
					end
					#s1= s1.split(//)
					#s1= s1.sort.join.downcase
					s2=words[k].to_s
					if s2.length>1
						s2= s2.downcase.split(//)
						s2= s2.sort.join
					else
						s2=s2.downcase
					end
					
					if (s1==s2)
						u = Array[a[i],words[k]]
						a[i]= u
						found = true
                	end  
					break if found
				end
			break if found
			end
		if !found
			s=Array[words[k]]
			a[a.length] = s
		end	
		end
	end
return a
end
