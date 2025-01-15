FROM node:lts-alpine

RUN apk add --no-cache libc6-compat

COPY . .

RUN npm ci 
RUN npm run build

CMD ["npm", "run", "start"]