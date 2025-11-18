class Solution:
    def isNumber(self, s: str) -> bool:
        s = s.strip()

        num = False    
        dot = False    
        exp = False    
        sign = False  

        for i, c in enumerate(s):
            if c.isdigit():
                num = True

            elif c in ['+', '-']:
                if i > 0 and s[i-1].lower() not in ['e']:
                    return False

            elif c == '.':
                if dot or exp:
                    return False
                dot = True

            elif c.lower() == 'e':
                if exp or not num:
                    return False
                exp = True
                num = False   

            else:
                return False

        return num
