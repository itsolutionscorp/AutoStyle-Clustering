def combine_anagrams(words)
	a=Array.new
	a[0]=Array.new
	a[1]=Array.new
	a[2]=Array.new
	ans=Array.new
	count=0
	j=0
	x=-1
	c=0
	words.each { |word|  
		a[0][count]=word;
		a[2][count]=1;
		a[1][count]=word.downcase.chars.sort.join;
		count+=1;
		}
	count-=1;
	for i in 0..count
		if(a[2][i]==1) then
			x+=1
			c=0
			ans[x]=Array.new
			ans[x][c]=a[0][i]
			for j in (i+1)..count
				if (a[1][i] == a[1][j]) then
					c+=1
					ans[x][c] =a[0][j]
					a[2][j]=0
				end
			end
		end
	end
	
	return ans
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])